import { Kafka } from 'kafkajs';

export const kafka = new Kafka({
  clientId: 'order-service',
  brokers: ['localhost:9092'] // cambia a 'kafka:9092' si estás usando Docker Compose
});
