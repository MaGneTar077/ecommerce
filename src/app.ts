import express from 'express';
import orderRoutes from './interfaces/routes/orderRoutes';

const app = express();
app.use(express.json());
app.use('/api', orderRoutes);

export default app;
