import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import Select from '@mui/material/Select'
import MenuItem from '@mui/material/MenuItem'
import PropTypes from 'prop-types'

export default function CurrencySelector(props) {
  const { updateCurrencyFunction } = props
  CurrencySelector.propTypes = {
    updateCurrencyFunction: PropTypes.func,
  }
  const { id } = useParams()
  const apiURL =
    'https://api.coingecko.com/api/v3/coins/markets?vs_currency=USD&order=market_cap_desc&per_page=100&page=1&sparkline=fals'
  // USER CHOICE OF CURRENCY
  const [currencyChoice, setCurrencyChoice] = useState(id)
  // USER CHOICE OF ACCOUNTS
  const [currencyList, setCurrencyList] = useState([])

  // GET ALL ACCOUNTS AVAILABLE, only activate at refresh
  useEffect(() => {
    fetch(apiURL)
      .then((response) => response.json())
      .then((json) => {
        setCurrencyList(json)
      })
  }, [])

  // SET CURRENCY CHOICE
  const handleChange = (event) => {
    setCurrencyChoice(event.target.value)
    updateCurrencyFunction(event.target.value)
  }

  return (
    <>
      <Select
        labelId="currencyChoice"
        id="currencyChoice"
        value={currencyChoice}
        onChange={handleChange}
        label="currencyChoice"
        autoWidth
      >
        {currencyList.map((row, index) => {
          return (
            <MenuItem value={row.id} key={row.id}>
              <div>Coin: {row.id}</div>
              {/* <div>Account Balance: {row.accountBalance}</div> */}
            </MenuItem>
          )
        })}
      </Select>
    </>
  )
}
