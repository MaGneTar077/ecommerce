import { kafka } from '../config/kafka';

const producer = kafka.producer();

export const connectKafkaProducer = async (retry = 0) => {
  try {
    await producer.connect();
    console.log('✅ Kafka Producer conectado');
  } catch (error: unknown) {
    if (error instanceof Error) {
      console.error(`❌ Error al conectar con Kafka: ${error.message}`);
    } else {
      console.error('❌ Error desconocido al conectar con Kafka');
    }
    
    if (retry < 10) {
      const waitTime = 3000;
      console.log(`🔄 Reintentando conexión en ${waitTime / 1000}s... (Intento ${retry + 1}/10)`);
      setTimeout(() => connectKafkaProducer(retry + 1), waitTime);
    } else {
      console.error('🚫 No se pudo conectar a Kafka después de múltiples intentos.');
      process.exit(1);
    }
  }
};

export const sendKafkaMessage = async (topic: string, message: any) => {
  await producer.send({
    topic,
    messages: [{ value: JSON.stringify(message) }]
  });
  console.log(`📤 Evento enviado a Kafka: ${topic}`);
};

export { producer };
