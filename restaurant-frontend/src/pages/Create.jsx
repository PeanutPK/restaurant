import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import api from '../api/axios'

export default function Create() {
  const [user, setUser] = useState(null)
  const [loading, setLoading] = useState(true)
  const navigate = useNavigate()

  const [name, setName] = useState('')
  const [rating, setRating] = useState(0.0)
  const [location, setLocation] = useState('')

  useEffect(() => {
    async function init() {
      try {
        // 1. Validate user log in
        const me = await api.get('/api/auth/me')
        setUser(me.data)

        if (me.data.role.toUpperCase() !== 'ADMIN') {
          navigate('/restaurant')
        }

      } catch (error) {
        console.error(error)
        navigate('/login') // if not authenticated, go to login
      } finally {
        setLoading(false)
      }
    }

    init()
  }, [navigate])

  async function handleLogout() {
    try {
      await api.post('/api/auth/logout')
    } catch (_) {}
    navigate('/login')
  }

  async function handleAddRestaurant() {
    try {
      if (!name || !rating || !location) {
        console.log("No data\nname:", name, "\nrating:", rating, "\nlocation:", location)
      }
      const data = {
        name: name,
        rating: rating,
        location: location
      }
      await api.post('/api/restaurants', data)
      console.log("Success")
    } catch (_) {
      console.error(_)
    }
  }

  const handleNameChange = (event) => {
    setName(event.target.value);
  }
  const handleRatingChange = (event) => {
    setRating(event.target.value)
  }
  const handleLocationChange = (event) => {
    setLocation(event.target.value)
  }

  if (loading) {
    return <div style={{ padding: '2rem' }}>Loading...</div>
  }

  return (
    <div style={{ padding: '2rem' }}>
      <h1>Create Restaurant</h1>
      <p>Welcome, <strong>{user.role} {user.username}</strong></p>

      <div style={{ marginBottom: '1rem', display: 'block' }}>
        <label>Name:</label>
        <br/>
        <input
          type="text"
          placeholder="Name"
          value={name}
          onChange={handleNameChange}
          style={{ marginBottom: '0.5rem' }}
        />
        <br/>

        <label>Rating:</label>
        <br/>
        <input
          type="number"
          placeholder="Rating"
          value={rating}
          onChange={handleRatingChange}
          style={{ marginBottom: '0.5rem' }}
        />
        <br/>

        <label>Location:</label>
        <br/>
        <input
          type="text"
          placeholder="Location"
          value={location}
          onChange={handleLocationChange}
          style={{ marginBottom: '0.5rem' }}
        />
        <br/>
      </div>

      <div style={{ marginBottom: '1rem' }}>
        <button onClick={handleAddRestaurant} style={{marginRight: '1rem', backgroundColor: 'green'}}>
          Submit
        </button>

        <button onClick={handleLogout} style={{backgroundColor: 'orange'}}>
          Logout
        </button>
      </div>
    </div>
  )
}
