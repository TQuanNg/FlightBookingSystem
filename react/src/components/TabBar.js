import React, {useState} from 'react';

export const TabBar = ({tabContent, onChange}) => {
    const [currentTabIndex, setCurrentTabIndex] = useState(0);

    const handleTabClick = (currentIndex) => {
        setCurrentTabIndex(currentIndex)
        onChange(currentIndex); // parent child relationship, for purpose of side effect
    }

    return (
        <div className="TabBar">
            <div className="TabNavigation">
                {tabContent.map((tabItem, index) => (
                    <div className={`tab-item ${currentTabIndex=== index ? "active" : ""}`}
                    key = {tabItem.label} 
                    onClick={() => handleTabClick(index)}>
                        <span className='TabLabel'> {tabItem.label} </span>
                    </div>
                ))}
            </div>

            <div className="TabContent"> {/* if index valid then load its content*/}
                {tabContent[currentTabIndex] && tabContent[currentTabIndex].content}
            </div>
        </div>
    )

}