import { Order } from '../models/Order';

export interface OrderRepository {
  save(order: Order): Promise<void>;
}
