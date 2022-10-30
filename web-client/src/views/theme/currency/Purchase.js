import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import useFetch from 'use-http'

import Grid2 from '@mui/material/Unstable_Grid2' // Grid version 2
import Box from '@mui/material/Box'
import { Button } from '@mui/material'

export default function Purchase() {
  const { id } = useParams()
  const apiURL = 'http://localhost:3000/coins'
  var queryURL = apiURL + '?id=' + id

  // Retrieve Currency Data
  const [cdata, setCdata] = useState([])
  // TODO: Prevent refresh from timer dependency useEffect
  useEffect(() => {
    fetch(queryURL)
      .then((response) => response.json())
      .then((json) => setCdata(json))
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
  const [countDown, setCountDown] = useState(0)
  const [runTimer, setRunTimer] = useState(true)

  useEffect(() => {
    let timerId

    if (runTimer) {
      setCountDown(60 * 5)
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

  const togglerTimer = () => setRunTimer((t) => !t)

  const seconds = String(countDown % 60).padStart(2, 0)
  const minutes = String(Math.floor(countDown / 60)).padStart(2, 0)

  return (
    <>
      {/* {cdata[0] && <div>{cdata[0].name}</div>} */}
      <form className="purchase-coin">
        <h1>Confirm Purchase</h1>
        <div className="App">
          {runTimer ? (
            <div>
              Valid for: {minutes}:{seconds}
            </div>
          ) : (
            <buttton type="button" onClick="window.location.reload();">
              Restart
            </buttton>
          )}
        </div>
        <Grid2 container spacing={2}>
          <Grid2 xs={2}></Grid2>
          <Grid2 xs={8} sx={{ alignContent: 'center' }}>
            <Box component="span" sx={{ justifyContent: 'center' }}>
              <Grid2 container spacing={2}>
                <Grid2 xs={6}>
                  <img
                    src="https://freepngimg.com/save/62891-icon-icons-money-computer-save-payment-software/1065x823"
                    alt="coin_img"
                  />
                </Grid2>
                <Grid2 xs={6}>
                  <img src={cdata[0] && cdata[0].image} alt="crypt_img" />
                </Grid2>
                <Grid2 xs={6}>
                  <input type="number" onChange={(e) => convertValue(e.target.value)} />
                </Grid2>
                <Grid2 xs={6}>{converted} coin</Grid2>
              </Grid2>
            </Box>
          </Grid2>
          <Grid2 xs={2}></Grid2>
        </Grid2>
        <h3>
          Price: 1 {cdata[0] && cdata[0].symbol} = ${cdata[0] && cdata[0].current_price}
        </h3>
        <h3>
          You Receive: {converted} {cdata[0] && cdata[0].symbol}
        </h3>
        <input type="submit" value="Submit" />
      </form>
    </>
  )
}
