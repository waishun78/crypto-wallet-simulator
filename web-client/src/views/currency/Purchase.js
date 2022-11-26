import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import useFetch from 'use-http'

import Grid2 from '@mui/material/Unstable_Grid2' // Grid2 version 2
import { Button, Box, IconButton } from '@mui/material'
import { styled } from '@mui/material/styles'
import FormControl from '@mui/material/FormControl'
import OutlinedInput from '@mui/material/OutlinedInput'
import AccessAlarmIcon from '@mui/icons-material/AccessAlarm'

import MenuItem from '@mui/material/MenuItem'
import Select from '@mui/material/Select'

import axios from 'axios'

const Img = styled('img')({
  margin: 'auto',
  display: 'block',
  maxWidth: '50%',
  maxHeight: '50%',
  marginTop: '40px',
  marginBottom: '40px',
})

export default function Purchase() {
  const { id } = useParams()
  const apiURL =
    'https://api.coingecko.com/api/v3/coins/markets?vs_currency=USD&order=market_cap_desc&per_page=100&page=1&sparkline=fals'
  var queryURL = apiURL + '?id=' + id

  const accountsQueryURL = 'http://localhost:8080/api/v1/accounts'

  // Retrieve Currency Data
  const [cdata, setCdata] = useState([])
  const [accountData, setAccountData] = useState([])

  // TODO: Prevent refresh from timer dependency useEffect
  useEffect(() => {
    fetch(queryURL)
      .then((response) => response.json())
      .then((json) => setCdata(json))
  }, [])

  const [accountChoice, setAccountChoice] = useState('Invalid Account')
  useEffect(() => {
    fetch(accountsQueryURL)
      .then((response) => response.json())
      .then((json) => setAccountData(json))
  }, [])
  console.log(queryURL)
  console.log(id)
  console.log(cdata)

  // Convert Currency
  const [converted, setConverted] = useState(0)
  function convertValue(v) {
    console.log(v / cdata[0].current_price)
    setConverted(v / cdata[0].current_price)
  }

  // Timer
  const [countDown, setCountDown] = useState(10)
  const [runTimer, setRunTimer] = useState(true)

  useEffect(() => {
    let timerId

    if (runTimer) {
      setCountDown(300)
      timerId = setInterval(() => {
        setCountDown((countDown) => countDown - 1)
      }, 1000)
    } else {
      clearInterval(timerId)
    }

    return () => clearInterval(timerId)
  }, [runTimer])

  useEffect(() => {
    if (countDown < 0 && runTimer) {
      console.log('expired')
      setRunTimer(false)
      setCountDown(0)
    }
  }, [countDown, runTimer])

  const seconds = String(countDown % 60).padStart(2, 0)
  const minutes = String(Math.floor(countDown / 60)).padStart(2, 0)

  const handleChange = (event) => {
    setAccountChoice(event.target.value)
  }

  const makeTransaction = (event, account, cdata, converted) => {
    // Compare the account for its indformation and check whether it has enough currency
    var amount_needed = cdata[0].current_price * converted
    for (var i = 0; i < accountData.length; i++) {
      console.log(i)
      if (accountData[i]['username'] === account) {
        var amount_owned = accountData[i]['accountBalance']
      }
    }
    if (amount_owned >= amount_needed) {
      // ENOUGH MONEY - Create asset OR update asset with new quantity
      var assetQueryURl = 'http://localhost:8080/api/v1/assets?username=' + account
      var assets = fetch(assetQueryURl).then((response) => response.json())
      //TODO: Find exsiting asset if it exists and create if no corresponding asset
      // CHEAT METHOD: Just create asset
      var assetCreatURL = 'http://localhost:8080/api/v1/assets'
      console.log({
        account: account,
        crypto_id: id,
        crypto_name: cdata,
        quantity: converted,
      })
      axios.post(assetCreatURL, {
        account: account,
        crypto_id: id,
        crypto_name: id,
        quantity: converted,
      })
    } else {
      // NOT ENOUGH MONEY
      window.alert('Insufficient Funds')
    }
  }

  return (
    <>
      {/* {cdata[0] && <div>{cdata[0].name}</div>} */}
      <h1>Confirm Purchase</h1>
      <Select
        labelId="accountChoice"
        id="accountChoice"
        value={accountChoice}
        label="Account"
        onChange={handleChange}
      >
        {accountData.map((row, index) => {
          return (
            <MenuItem value={row.username} key={row.username}>
              <div>Username: {row.username}</div>
              <div>Account Balance: {row.accountBalance}</div>
            </MenuItem>
          )
        })}
      </Select>
      <Grid2
        container
        spacing={2}
        direction="row"
        justify="center"
        justifyContent="center"
        alignItems="center"
        alignContent="center"
      >
        <Box
          bgcolor="white"
          sx={{
            boxShadow: 3,
            borderRadius: '10px',
            width: 7 / 8,
            mx: 'auto',
            maxWidth: 2000,
            mt: 5,
          }}
          color="white"
        >
          <Grid2
            container
            spacing={4}
            direction="row"
            justify="center"
            justifyContent="center"
            alignItems="center"
            alignContent="center"
            textAlign="center"
          >
            <Grid2 h1 xs={4}>
              <Img src="https://cdn-icons-png.flaticon.com/512/3154/3154207.png" alt="coin_img" />
            </Grid2>
            <Grid2 h1 xs={2}>
              <Img
                src="https://parspng.com/wp-content/uploads/2021/11/arrowpng.parspng.com_.png"
                alt="arrow_img"
              />
            </Grid2>
            <Grid2 h1 xs={4}>
              <Img src={cdata[0] && cdata[0].image} alt="crypto_img" />
            </Grid2>
          </Grid2>
        </Box>
        <Box
          sx={{
            width: 11 / 12,
            mx: 'auto',
            maxWidth: 2000,
            mt: 5,
          }}
        >
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
              Price:
            </Grid2>
            <Grid2 h1 xs={6} textAlign="right">
              1 {cdata[0] && cdata[0].symbol} = ${cdata[0] && cdata[0].current_price}
            </Grid2>
            <Grid2 xs={6} align="center" justifyContent="center" justifyItems="center">
              You pay:
            </Grid2>
            <Grid2 h1 xs={6} textAlign="right">
              <FormControl sx={{ width: '25ch' }}>
                <OutlinedInput
                  type="number"
                  placeholder="Please enter amount"
                  onChange={(e) => convertValue(e.target.value)}
                />
              </FormControl>
            </Grid2>
            <Grid2 h1 xs={6} align="left">
              Your Receive:
            </Grid2>
            <Grid2 h1 xs={6} textAlign="right">
              {converted} {cdata[0] && cdata[0].symbol}
            </Grid2>
            <Grid2 h1 xs={12} textAlign="center">
              {runTimer ? (
                <div>
                  Valid for: {minutes}:{seconds}
                </div>
              ) : (
                <Button type="button" onClick={() => window.location.reload()} color="error">
                  <IconButton color="secondary" aria-label="add an alarm">
                    <AccessAlarmIcon color="disabled" />
                  </IconButton>
                  Refresh
                </Button>
              )}
              <Button
                type="submit"
                value="Submit"
                align="center"
                disabled={!runTimer}
                onClick={(event) => makeTransaction(event, accountChoice, cdata, converted)}
              >
                Submit
              </Button>
            </Grid2>
          </Grid2>
        </Box>
      </Grid2>
    </>
  )
}
