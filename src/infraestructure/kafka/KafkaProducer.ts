import { kafka } from '../config/kafka';

const producer = kafka.producer();

export const connectKafkaProducer = async () => {
  await producer.connect();
  console.log('✅ Kafka Producer conectado');
};

export const sendKafkaMessage = async (topic: string, message: any) => {
  await producer.send({
    topic,
    messages: [{ value: JSON.stringify(message) }]
  });
  console.log(`📤 Evento enviado a Kafka: ${topic}`);
};

export { producer };
