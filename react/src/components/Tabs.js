import { TabBar } from "./TabBar";
import { OneWaySearch } from "./OneWaySearch";
import { RoundTripSearch } from "./RoundTripSearch";

export default function Tabs() {
    const tabs = [
        {
          label: "Roundtrip",
          content: <RoundTripSearch />,
        },
        /*{
          label: "One-way",
          content: <OneWaySearch />,
        },*/
    ]

    function handleChange(currentTabIndex) {
        console.log(currentTabIndex);
      }

    return <TabBar tabContent={tabs} onChange={handleChange} />;
}