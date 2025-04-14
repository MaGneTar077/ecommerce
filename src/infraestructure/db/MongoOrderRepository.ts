import { Order } from '../../domain/models/Order';
import { OrderModel } from './schemas/OrderSchema';
import { OrderRepository } from '../../domain/repositories/OrderRepository';

export class MongoOrderRepository implements OrderRepository {
  async save(order: Order): Promise<void> {
    const orderDoc = new OrderModel({
      userId: order.userId,
      items: order.items,
      total: order.total,
      payload: order.payload,
      snapshot: order.snapshot,
    });

    await orderDoc.save();
  }
}
