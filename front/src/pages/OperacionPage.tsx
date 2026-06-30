import axios from 'axios'
import { useState, type FormEvent } from 'react'
import { registerOperacion } from '../api/operacion.api'
import { encryptAes256Ecb } from '../utils/aes'
import './OperacionPage.css'

interface Notification {
  type: 'success' | 'error'
  message: string
}

export function OperacionPage() {
  const [operacion, setOperacion] = useState('')
  const [importe, setImporte] = useState('')
  const [cliente, setCliente] = useState('')
  const [secreto, setSecreto] = useState('')
  const [loading, setLoading] = useState(false)
  const [notification, setNotification] = useState<Notification | null>(null)

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault()
    setNotification(null)
    setLoading(true)

    try {
      const response = await registerOperacion({
        operacion,
        importe: Number(importe),
        cliente,
        secreto: encryptAes256Ecb(secreto),
      })

      setNotification({
        type: 'success',
        message: `Operación registrada. ID: ${response.id}, Estatus: ${response.estatus}, Referencia: ${response.referencia}`,
      })
      setOperacion('')
      setImporte('')
      setCliente('')
      setSecreto('')
    } catch (error) {
      const message = axios.isAxiosError(error)
        ? (error.response?.data?.message ??
          error.response?.data?.error ??
          'Error al registrar la operación')
        : error instanceof Error
          ? error.message
          : 'Error al registrar la operación'

      setNotification({ type: 'error', message: String(message) })
    } finally {
      setLoading(false)
    }
  }

  return (
    <main className="operacion-page">
      <form className="operacion-form" onSubmit={handleSubmit}>
        <h1>Registrar operación</h1>

        <label htmlFor="operacion">Operación</label>
        <input
          id="operacion"
          type="text"
          value={operacion}
          onChange={(event) => setOperacion(event.target.value)}
          required
        />

        <label htmlFor="importe">Importe</label>
        <input
          id="importe"
          type="number"
          step="0.01"
          min="0"
          value={importe}
          onChange={(event) => setImporte(event.target.value)}
          required
        />

        <label htmlFor="cliente">Cliente</label>
        <input
          id="cliente"
          type="text"
          value={cliente}
          onChange={(event) => setCliente(event.target.value)}
          required
        />

        <label htmlFor="secreto">Secreto</label>
        <input
          id="secreto"
          type="text"
          value={secreto}
          onChange={(event) => setSecreto(event.target.value)}
          required
        />

        <button type="submit" disabled={loading}>
          {loading ? 'Enviando...' : 'Registrar'}
        </button>
      </form>

      {notification && (
        <div
          className={`operacion-notification operacion-notification--${notification.type}`}
          role="alert"
        >
          <p>{notification.message}</p>
          <button
            type="button"
            onClick={() => setNotification(null)}
            aria-label="Cerrar notificación"
          >
            ×
          </button>
        </div>
      )}
    </main>
  )
}
