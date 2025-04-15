import { Kafka } from 'kafkajs';

// Lee el broker desde .env
const kafkaBrokers = process.env.KAFKA_BROKER?.split(',') || ['localhost:9092'];

export const kafka = new Kafka({
  clientId: 'order-service',
  brokers: kafkaBrokers
});
