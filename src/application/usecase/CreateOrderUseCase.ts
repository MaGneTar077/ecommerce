import { Order } from '../../domain/models/Order';
import { OrderRepository } from '../../domain/repositories/OrderRepository';
import { InvoiceProducer } from '../../infraestructure/kafka/InvoiceProducer';

export class CreateOrderUseCase {
  constructor(private orderRepo: OrderRepository) {}

  async execute(order: Order) {
    await this.orderRepo.save(order);

    const invoiceProducer = new InvoiceProducer();
    await invoiceProducer.connect();
    await invoiceProducer.sendInvoiceProcessingEvent(order);
    await invoiceProducer.disconnect();
  }
}
