import React, { useState, useEffect } from 'react'
import Select from '@mui/material/Select'
import MenuItem from '@mui/material/MenuItem'
import PropTypes from 'prop-types'

export default function AccountSelector(props) {
  const { updateAccount } = props
  AccountSelector.propTypes = {
    updateAccount: PropTypes.func,
  }

  const accountsQueryURL = 'http://localhost:8080/api/v1/accounts'

  // LIST OF ALL AVAILABLE ACCOUNTS
  const [accountData, setAccountData] = useState([])
  // USER CHOICE OF ACCOUNTS
  const [accountChoice, setAccountChoice] = useState('')

  // GET ALL ACCOUNTS AVAILABLE, only activate at refresh
  useEffect(() => {
    fetch(accountsQueryURL)
      .then((response) => response.json())
      .then((json) => {
        setAccountData(json)
      })
  }, [])

  // SET ACCOUNT CHOICE
  const handleChange = (event) => {
    setAccountChoice(event.target.value)
    updateAccount(event.target.value)
  }

  return (
    <>
      <Select
        labelId="accountChoice"
        id="accountChoice"
        value={accountChoice}
        onChange={handleChange}
        defaultValue={'Account'}
        label="Account"
        autoWidth
      >
        {accountData.map((row, index) => {
          return (
            <MenuItem value={row.username} key={row.username}>
              <div>Username: {row.username}</div>
              {/* <div>Account Balance: {row.accountBalance}</div> */}
            </MenuItem>
          )
        })}
      </Select>
    </>
  )
}
