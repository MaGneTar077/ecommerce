import { Request, Response } from 'express';
import { MongoOrderRepository } from '../../infraestructure/db/MongoOrderRepository';
import { CreateOrderUseCase } from '../../application/usecase/CreateOrderUseCase';
import { Order } from '../../domain/models/Order';
import { producer } from '../../infraestructure/kafka/KafkaProducer';
import { v4 as uuidv4 } from 'uuid'; // Asegúrate de tener 'uuid' instalado

export class OrderController {
  static async createOrder(req: Request, res: Response) {
    try {
      const { userId, items } = req.body;

      const total = items.reduce((sum: number, item: any) => sum + item.quantity * item.price, 0);

      // 🔸 Crear orden en la base de datos
      const order = new Order(userId, items, total, null, null);
      const repo = new MongoOrderRepository();
      const usecase = new CreateOrderUseCase(repo);
      await usecase.execute(order);

      // 🔧 Construir evento completo
      const eventId = uuidv4();
      const createdAt = new Date().toISOString();

      const kafkaEvent = {
        eventId,
        timestamp: createdAt,
        source: 'OrderService',
        topic: 'order_created',
        payload: {
          userId,
          items,
          tipoEntrega: 'express',
          comentario: 'Dejar en la portería'
        },
        snapshot: {
          estado: 'pendiente',
          total,
          fecha: createdAt
        }
      };

      // 🚀 Enviar evento unificado a Kafka
      await producer.send({
        topic: 'order_created',
        messages: [
          {
            value: JSON.stringify(kafkaEvent)
          }
        ]
      });

      console.log('📨 Evento unificado enviado a Kafka (order_created)');

      res.status(201).json({ message: 'Orden creada correctamente' });
    } catch (err) {
      console.error('❌ Error al crear la orden:', err);
      res.status(500).json({ error: 'Error al crear la orden' });
    }
  }
}
