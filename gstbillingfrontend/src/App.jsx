import { useState } from 'react'
import heroImg from './assets/hero.png'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import './App.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import { SignIn } from './components/SignIn'
import { SignUp } from './components/SignUp'
import { CreateInvoice } from './components/CreateInvoice'
import { ShowInvoices } from './components/ShowInvoices'
import { EditInvoice } from './components/EditInvoice'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>

      <Router>
        <Routes>
          <Route exact path='/' element={<SignIn />} />
          <Route exact path='/signup' element={<SignUp />} />
          <Route exact path='/create-invoice' element={<CreateInvoice />} />
          <Route exact path='/show' element={<ShowInvoices />} />
          <Route exact path='/edit/:invoiceId' element={<EditInvoice />} />
        </Routes>
      </Router>
    </>
  )
}

export default App
