import './App.css'
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'
import FetchPosts from './components/FetchPosts'
import AxiosPosts from './components/AxiosPosts'
import PostManager from './components/PostManager'

function App() {
  return (
    <>
      <BrowserRouter>
        <nav>
          <h1>Get com Fetch e Axios</h1>
          <div>
            <Link to="/fetch-posts">Fetch Posts</Link>
          </div>
          <div>
            <Link to="/axios-posts">Axios Posts</Link>
          </div>
          {/* POST, PUT e DELETE */}
          <div>
            <Link to="/posts">Gerenciar Postagens</Link>
          </div>
        </nav>

        <Routes>
          <Route path="/fetch-posts" element={<FetchPosts />} />
          <Route path="/axios-posts" element={<AxiosPosts />} />
          {/* POST, PUT e DELETE */}
          <Route path="/posts" element={<PostManager />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
