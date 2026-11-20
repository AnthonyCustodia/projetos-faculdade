import './App.css'

import { BrowserRouter, Routes, Route,Link } from 'react-router-dom'
import FetchPosts from './components/FetchPosts'

function App() {
  
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
        <Route path="/fetch-posts" element={<FetchPosts/>}></Route>
      </Routes>
    </BrowserRouter>
      
    </>
  )
}

export default App
