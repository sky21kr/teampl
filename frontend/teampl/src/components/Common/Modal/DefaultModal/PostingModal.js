import React, { useState } from 'react';
import './PostingModal.scss'
import CloseBtn from '@/assets/images/close.svg'


const DefaultModal = (props) => {

    const [text,setText] = useState('');
    const [title,setTitle] = useState('');

    const [content,setContent] = useState({
        title:'',
        text:''
    })

    const [postContent ,setPostContent] = useState([]);

    const onChangeTitle = (e)=>{
        const {name,value} = e.target;
        setContent({
            ...content,
            [name]:value
        })
        console.log(content)
    }

    const onChangeText = (e)=>{
        const {name,value} = e.target;
        setContent({
            ...content,
            [name]:value
        })
        console.log(content)
    }


   const handleSubmit = (event)=>{
        // event.preventDefault()
    }

    return (
        <div className={ props.showModal ? "defaultModalBackground" : "hidden"}>
            <div className="postingModal">
                <img className="closeBtn-a" onClick={props.closeModal} src={CloseBtn}/>

                <div className="modalTitle">{props.title}</div>

                    <form onSubmit={handleSubmit} >
                        <input name='title' onChange={onChangeTitle} placeholder="제목"></input>
                        <textarea name='text' onChange={onChangeText} placeholder="내용"></textarea>
                        <button type="submit" 
                        onClick={ ()=> {setPostContent (postContent.concat({...content}));}}>등록하기</button>
                    </form>
               
            </div>
        </div>
    )
}

export default DefaultModal;