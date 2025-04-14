import { Request, Response } from 'express';
import { MongoOrderRepository } from '../../infraestructure/db/MongoOrderRepository';
import { CreateOrderUseCase } from '../../application/usecase/CreateOrderUseCase';
import { Order } from '../../domain/models/Order';
import { producer } from '../../infraestructure/kafka/KafkaProducer';

export class OrderController {
  static async createOrder(req: Request, res: Response) {
    try {
      const { userId, items } = req.body;

      const total = items.reduce((sum: number, item: any) => sum + item.quantity * item.price, 0);

      // 🔸 Crear Order sin payload ni snapshot en el modelo
      const order = new Order(userId, items, total, null, null);

      const repo = new MongoOrderRepository();
      const usecase = new CreateOrderUseCase(repo);
      await usecase.execute(order);

      const createdAt = new Date().toISOString();

      // 🔵 Enviar evento de orden creada
      await producer.send({
        topic: 'order_created',
        messages: [
          {
            value: JSON.stringify({
              userId,
              items,
              total,
              createdAt,
            }),
          },
        ],
      });

      // 🟢 Enviar evento de payload (automático)
      const payload = {
        tipoEntrega: 'express',
        comentario: 'Dejar en la portería',
        userId,
      };

      await producer.send({
        topic: 'order_payload',
        messages: [{ value: JSON.stringify(payload) }],
      });

      // 🟠 Enviar evento de snapshot (automático)
      const snapshot = {
        estado: 'pendiente',
        fecha: createdAt,
        userId,
        total,
      };

      await producer.send({
        topic: 'order_snapshot',
        messages: [{ value: JSON.stringify(snapshot) }],
      });

      console.log('📨 Evento enviado a Kafka (order_created)');
      console.log('📨 Evento enviado a Kafka (order_payload)');
      console.log('📨 Evento enviado a Kafka (order_snapshot)');

      res.status(201).json({ message: 'Orden creada correctamente' });
    } catch (err) {
      console.error('❌ Error al crear la orden:', err);
      res.status(500).json({ error: 'Error al crear la orden' });
    }
  }
}
