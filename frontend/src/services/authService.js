import api from './api';

const login = async (username, password) => {
  const response = await api.post('/users/login', { username, password });
  if (response.data.accessToken) {
    localStorage.setItem('user', JSON.stringify(response.data));
  }
  return response.data;
};

const register = async (username, password, email) => {
  return await api.post('/users/register', { username, password, email });
};

const logout = () => {
  localStorage.removeItem('user');
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem('user'));
};

export default {
  login,
  register,
  logout,
  getCurrentUser,
};
