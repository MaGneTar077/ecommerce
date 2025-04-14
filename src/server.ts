import app from './app';
import { connectDatabase } from './infraestructure/config/database';
import { connectKafkaProducer } from './infraestructure/kafka/KafkaProducer';
import { startOrderCreatedConsumer } from './infraestructure/kafka/orderCreated.consumer';


const PORT = 3000;

async function start() {
  try {
    await connectDatabase();
    await connectKafkaProducer(); // Establecer conexión con Kafka
    await startOrderCreatedConsumer(); // ✅ Aquí


    app.listen(PORT, () => {
      console.log(`🚀 Servidor corriendo en http://localhost:${PORT}`);
    });
  } catch (err) {
    console.error('❌ Error al iniciar el servicio:', err);
  }
}

start();
