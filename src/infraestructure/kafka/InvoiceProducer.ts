import { Kafka } from "kafkajs";
import { InvoiceProcessingEvent } from "../../domain/events/InvoiceProcessingEvent";
import { v4 as uuidv4 } from "uuid";

export class InvoiceProducer {
  private kafka = new Kafka({
    clientId: "order-service",
    brokers: ["kafka:9092"]
  });

  private producer = this.kafka.producer();

  async connect() {
    await this.producer.connect();
  }

  async sendInvoiceProcessingEvent(order: any) {
    const event: InvoiceProcessingEvent = {
      eventId: uuidv4(),
      timestamp: new Date().toISOString(),
      source: "order-service",
      topic: "invoice-processing",
      payload: {
        userId: order.userId,
        items: order.items,
        total: order.total
      },
      snapshot: {
        status: "PENDING"
      }
    };

    await this.producer.send({
      topic: "invoice-processing",
      messages: [
        {
          key: event.eventId,
          value: JSON.stringify(event)
        }
      ]
    });

    console.log("Evento enviado a invoice-processing");
  }

  async disconnect() {
    await this.producer.disconnect();
  }
}
