export interface OrderCreatedEvent {
    eventId: string;
    timestamp: string;
    source: string;
    topic: string;
    payload: any;
    snapshot: any;
  }
  