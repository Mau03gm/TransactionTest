import { api } from './axios'
import {
  getStoredToken
} from './axios'


export interface OperacionRequest {
  operacion: string
  importe: number
  cliente: string
  secreto: string
}

export interface OperacionResponse {
  id: number
  estatus: string
  referencia: string
  operacion: string
}

export const registerOperacion = async (
  payload: OperacionRequest,
): Promise<OperacionResponse> => {
  
  const { data } = await api.post<OperacionResponse>(
    "/transactions",
    payload,
    {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${getStoredToken()}`,
      },
    }
  );
  return data
}
