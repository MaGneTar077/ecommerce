import mongoose from 'mongoose';

export async function connectDatabase() {
  try {
    await mongoose.connect('mongodb://localhost:27017/orders-db');
    console.log('📦 Conectado a MongoDB');
  } catch (err) {
    console.error('❌ Error conectando a MongoDB:', err);
  }
}
