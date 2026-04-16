import api from './api';

const getAllHotels = async (location = '') => {
  const response = await api.get('/hotels/search', {
    params: { location }
  });
  return response.data;
};

const getHotelDetails = async (id) => {
  const response = await api.get(`/hotels/${id}`);
  return response.data;
};

const bookRoom = async (bookingData) => {
  const response = await api.post('/bookings/book', bookingData);
  return response.data;
};

const getMyBookings = async (userId) => {
  const response = await api.get(`/bookings/history/${userId}`);
  return response.data;
};

export default {
  getAllHotels,
  getHotelDetails,
  bookRoom,
  getMyBookings,
};
