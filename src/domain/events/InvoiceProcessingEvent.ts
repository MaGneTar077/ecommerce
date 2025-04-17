export interface InvoiceProcessingEvent {
    eventId: string;
    timestamp: string;
    source: string;
    topic: string;
    payload: {
      userId: string;
      items: any[];
      total: number;
    };
    snapshot: {
      status: string;
    };
  }
  