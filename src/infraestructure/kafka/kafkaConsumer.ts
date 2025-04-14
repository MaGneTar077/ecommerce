import { Kafka } from 'kafkajs';

const kafka = new Kafka({
  clientId: 'orders-service',
  brokers: ['kafka:9092'],
});

const producer = kafka.producer();

const run = async () => {
  await producer.connect();
  await producer.send({
    topic: 'order_created',
    messages: [
      {
        value: JSON.stringify({ orderId: '123', item: 'Product' }),
      },
    ],
  });
  console.log('Mensaje enviado');
  await producer.disconnect();
};

run().catch(console.error);
