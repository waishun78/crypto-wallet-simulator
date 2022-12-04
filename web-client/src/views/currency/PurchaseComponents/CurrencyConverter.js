import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'

import Grid2 from '@mui/material/Unstable_Grid2' // Grid2 version 2
import { Box } from '@mui/material'
import { styled } from '@mui/material/styles'

import FormControl from '@mui/material/FormControl'
import OutlinedInput from '@mui/material/OutlinedInput'

import PropTypes from 'prop-types'

export default function CurrencyConverter(props) {
  const { updateParentAmount } = props
  CurrencyConverter.propTypes = {
    updateParentAmount: PropTypes.func,
  }
  const { id } = useParams()
  // BASED on this value, display the correct image
  const apiURL = 'https://api.coingecko.com/api/v3/coins'
  var queryURL = apiURL + '/' + id

  // DETAILS of particular currency
  const [currencyDetails, setCurrencyDetails] = useState({})
  // Convert Currency
  const [amount, setAmount] = useState(0)

  const Img = styled('img')({
    margin: 'auto',
    display: 'block',
    maxWidth: '50%',
    maxHeight: '50%',
    marginTop: '40px',
    marginBottom: '40px',
  })

  function updateAmount(v) {
    setAmount(v)
    updateParentAmount(v)
  }

  useEffect(() => {
    fetch(queryURL)
      .then((response) => response.json())
      .then((json) => {
        setCurrencyDetails(json)
        console.log(json)
        console.log(currencyDetails)
        console.log(Math.round(json.market_data.current_price.usd * 100) / 100)
      })
  }, [])

  return (
    <>
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
              <Img src={currencyDetails.image && currencyDetails.image.large} alt="crypto_img" />
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
              1 {currencyDetails && currencyDetails.symbol} = $
              {currencyDetails.market_data && currencyDetails.market_data.current_price.usd}
            </Grid2>
            <Grid2 xs={6} align="center" justifyContent="center" justifyItems="center">
              You pay:
            </Grid2>
            <Grid2 h1 xs={6} textAlign="right">
              <FormControl sx={{ width: '25ch' }}>
                <OutlinedInput
                  type="number"
                  placeholder="Please enter amount"
                  onChange={(e) => updateAmount(e.target.value)}
                />
              </FormControl>
            </Grid2>
            <Grid2 h1 xs={6} align="left">
              Your Receive (2 decimal place):
            </Grid2>
            <Grid2 h1 xs={6} textAlign="right">
              {currencyDetails.market_data &&
                // eslint-disable-next-line prettier/prettier
                Math.round((amount / currencyDetails.market_data.current_price.usd) * 100) / 100} {currencyDetails && currencyDetails.symbol}
            </Grid2>
          </Grid2>
        </Box>
      </Grid2>
    </>
  )
}
