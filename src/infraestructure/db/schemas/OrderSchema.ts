import mongoose from 'mongoose';

const OrderItemSchema = new mongoose.Schema({
  productId: String,
  quantity: Number,
  price: Number,
});

const OrderSchema = new mongoose.Schema({
  userId: String,
  items: [OrderItemSchema],
  total: Number,
  payload: { type: mongoose.Schema.Types.Mixed },  
    snapshot: { type: mongoose.Schema.Types.Mixed },
}, { timestamps: true });

export const OrderModel = mongoose.model('Order', OrderSchema);
