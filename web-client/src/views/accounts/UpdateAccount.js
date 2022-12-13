import React, { useState } from 'react'
import { useParams, useNavigate } from 'react-router-dom'

import axios from 'axios'

import { OutlinedInput } from '@material-ui/core'
import Grid2 from '@mui/material/Unstable_Grid2' // Grid2 version 2

import Button from '@mui/material/Button'

export default function UpdateAccount() {
  const { username } = useParams()
  console.log(username)
  const accountUpdateURL = 'http://localhost:8080/api/v1/accounts/' + username

  const [notes, setNotes] = useState()
  const [accountBalance, setAccountBalance] = useState()

  const navigate = useNavigate()

  const onSubmit = () => {
    console.log(username)
    console.log(notes)
    console.log(accountBalance)
    axios.put(accountUpdateURL, {
      notes: notes,
      accountBalance: accountBalance,
    })
    navigate('/accounts')
  }

  return (
    <>
      <h1>Update Account</h1>
      <Grid2
        container
        spacing={4}
        direction="row"
        justify="center"
        justifyContent="center"
        alignItems="center"
        alignContent="center"
      >
        <Grid2 h1 xs={6} align="left">
          Username:
        </Grid2>
        <Grid2 h1 xs={6} align="left">
          {username}
        </Grid2>
        <Grid2 h1 xs={6} align="left">
          Notes:
        </Grid2>
        <Grid2 h1 xs={6} align="left">
          <OutlinedInput
            placeholder="Add account notes"
            multiline={true}
            minRows="5"
            onChange={(e) => setNotes(e.target.value)}
          />
        </Grid2>
        <Grid2 h1 xs={6} align="left">
          Account Balance:
        </Grid2>
        <Grid2 h1 xs={6} align="left">
          <OutlinedInput
            type="number"
            placeholder="Enter starting account balance"
            onChange={(e) => setAccountBalance(e.target.value)}
          />
        </Grid2>
      </Grid2>
      <Button variant="contained" onClick={onSubmit}>
        Submit
      </Button>
    </>
  )
}
