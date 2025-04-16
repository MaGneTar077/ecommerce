import { Kafka } from 'kafkajs';

// 🚨 Verifica que la variable esté definida
if (!process.env.KAFKA_BROKER) {
  throw new Error('❌ Variable KAFKA_BROKER no definida en el entorno');
}

const kafkaBrokers = process.env.KAFKA_BROKER.split(',');

export const kafka = new Kafka({
  clientId: 'order-service',
  brokers: kafkaBrokers
});
