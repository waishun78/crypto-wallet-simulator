import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'

import { CContainer } from '@coreui/react'

import { Link } from 'react-router-dom'

import axios from 'axios'

import { alpha } from '@mui/material/styles'
import { OutlinedInput, TextField } from '@material-ui/core'
import Grid2 from '@mui/material/Unstable_Grid2' // Grid2 version 2

import Button from '@mui/material/Button'

export default function UpdateAccount() {
  const { username } = useParams()
  console.log(username)
  const accountUpdateURL = 'http://localhost:8080/api/v1/accounts/' + username

  const [notes, setNotes] = useState('')
  const [accountBalance, setAccountBalance] = useState(0)

  const onSubmit = () => {
    console.log(username)
    console.log(notes)
    console.log(accountBalance)
    axios.put(accountUpdateURL, {
      notes: notes,
      accountBalance: accountBalance,
    })
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
