import React , {useState} from 'react';
import './Alarm.scss'

function Alarm(props){


    //알람 있을때 alarm=true
    const [alarm,alarmHandler] = useState(false)

    const openAlarm = ()=>{
        alarmHandler(true)
    }

    const closeAlarm = ()=>{
        alarmHandler(false)
    }


    return(
        <div className="alarmWrap">
            <img src={ alarm ? "./images/alarmwithoutcircle.svg" :"./images/alarm.svg"  } onClick={openAlarm}></img>
           
            <div className={ alarm ? "alarmBox" : "closeAlarmBox"}>

                <div className="alarmTitle" >
                새로운 알람이 n개 있습니다. 
                <img src="./images/close.svg" onClick={closeAlarm}></img>
                </div>    
                <div className="alarmList">알람1</div>
                <div className="alarmList">알람2</div>
                <div className="alarmList">알람3</div>
                
            </div>


        </div>
    )
}

export default Alarm;