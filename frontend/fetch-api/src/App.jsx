import { useState } from 'react'
import './App.css'
import { BrowserRouter } from 'react-router-dom'
import FetchPosts from './components/FetchPosts'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <BrowserRouter>
        <nav>
          <h1>Get com Fetch e Axios</h1>
          <div>
            <Link to="/fetch-posts">Fetch Posts</Link>
          </div>
        </nav>
        <Routes>
          <Route path="/fetch-posts" element={<FetchPosts/>}/>
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
