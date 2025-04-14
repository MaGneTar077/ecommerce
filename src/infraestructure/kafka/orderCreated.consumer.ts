// src/infraestructure/kafka/orderCreated.consumer.ts
import { kafka } from '../config/kafka';

const consumer = kafka.consumer({ groupId: 'order-created-group' });

export const startOrderCreatedConsumer = async () => {
  await consumer.connect();
  await consumer.subscribe({ topic: 'order_created', fromBeginning: true });

  await consumer.run({
    eachMessage: async ({ topic, message }) => {
      const data = JSON.parse(message.value?.toString() || '{}');
      console.log('📥 Mensaje recibido:', data);

      // Aquí podrías pasar esto a un useCase o servicio de aplicación
      // por ejemplo: await orderService.handleOrderCreated(data);
    },
  });
};
