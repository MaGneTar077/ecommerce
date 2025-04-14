export interface OrderItem {
    productId: string;
    quantity: number;
    price: number;
  }
  
  export class Order {
    constructor(
      public readonly userId: string,
      public readonly items: OrderItem[],
      public readonly total: number,
      public readonly payload?: any,
      public readonly snapshot?: any
    ) {}
  }
  