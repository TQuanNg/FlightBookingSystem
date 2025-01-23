import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

import {HomePage} from './components/Home';
import {LoginPage} from './components/login/Login';
import {SignUpPage} from './components/login/SignUp';
import {AboutPage} from './components/About';
import {ErrorPage} from './components/ErrorPage';
import {PurchaseHistory} from './components/PurchaseHistory'
import { BookingSummary } from './components/BookingSummary';
import { ConfirmationPage } from './components/Confirmation';
import { BookingCart } from './components/BookingCart';
import { RouterProvider, createBrowserRouter} from "react-router-dom";


const router = createBrowserRouter([{
  path: '/',
  element: <App />,
  errorElement: <ErrorPage />
,
  children: [
    {
      index: true,  // This makes HomePage the default for the root path "/"
      element: <HomePage />,
    },
    {
      path: '/history',
      element: <PurchaseHistory />
    },
    {
      path: '/about',
      element: <AboutPage />,
    },
    {
      path: '/login',
      element: <LoginPage />,
    },
    {
      path: '/signup',
      element: <SignUpPage />
    },
    {
      path: '/booking-summary',
      element: <BookingSummary />
    },
    {
      path: '/confirmation',
      element: <ConfirmationPage />
    },
    {
      path: '/cart',
      element: <BookingCart />
    }
  ]
}]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <RouterProvider router={router}>
        <App />
    </RouterProvider>
);

{/*<React.StrictMode>
    <App />
  </React.StrictMode>*/}

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
