import React, { useState, useEffect } from 'react'
import { Button, IconButton } from '@mui/material'
import AccessAlarmIcon from '@mui/icons-material/AccessAlarm'

export default function Timer() {
  const [countDown, setCountDown] = useState(300)
  const [runTimer, setRunTimer] = useState(true)

  useEffect(() => {
    let timerId
    timerId = setInterval(() => {
      setCountDown((countDown) => countDown - 1)
    }, 1000)
    if (countDown < 0 && runTimer) {
      setRunTimer(false)
      setCountDown(0)
    }
    return () => clearInterval(timerId)
  }, [countDown, runTimer])
  const seconds = String(countDown % 60).padStart(2, 0)
  const minutes = String(Math.floor(countDown / 60)).padStart(2, 0)

  return (
    <>
      {runTimer ? (
        <div>
          Valid for: {minutes}:{seconds}
        </div>
      ) : (
        <Button type="button" onClick={() => window.location.reload()} color="error">
          <AccessAlarmIcon color="disabled" />
          Refresh
        </Button>
      )}
    </>
  )
}
