import { api } from './axios'

export interface LoginCredentials {
  username: string
  password: string
}

interface LoginResponse {
  token: string
}

export const loginRequest = async (
  credentials: LoginCredentials,
): Promise<string> => {
  const { data } = await api.post<LoginResponse>('/auth/login', credentials)
  return data.token
}
