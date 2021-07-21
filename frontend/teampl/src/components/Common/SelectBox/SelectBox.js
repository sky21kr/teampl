import React, { Component } from 'react'
import Select from 'react-select'

const SelectBox = ({ options, placeholder, onChange }) => {
    // const options = [
    //     { value: 'chocolate', label: 'Chocolate' },
    //     { value: 'strawberry', label: 'Strawberry' },
    //     { value: 'vanilla', label: 'Vanilla' }
    //   ]

      const customStyles = {
        menu: (provided, state) => ({
          ...provided,
        }),
      
        control: (provide) => ({
            ...provide,
            background: "#F7F5FF",
            border: 0,
            fontSize: '14px',
            boxShadow: "none",
            paddingLeft: 20,
        }),
      }
    return (
        <div style={{width: '100%'}}>
            <Select
                styles={customStyles}
                placeholder={placeholder}
                options={options}
                onChange={onChange}
            ></Select>
        </div>
    )
}

export default SelectBox;
