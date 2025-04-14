import { Order } from '../../domain/models/Order';
import { OrderRepository } from '../../domain/repositories/OrderRepository';

export class CreateOrderUseCase {
  constructor(private orderRepo: OrderRepository) {}

  async execute(order: Order) {
    await this.orderRepo.save(order);
  }
}
