import { useState } from 'react';
import { useQuery } from 'react-query';
import './App.css';

function App() {

  const [getId, setGetId] = useState("");
  const [getTitle, setGetTitle] = useState("");
  const [getResult, setGetResult] = useState(null);

  const formatResponse = res => {
    return JSON.stringify(res, null, 2);
  };

  const { isLoading: isLoadingTutorials, refetch: getAllTutorials} = useQuery(
    "query-tutorials",
    async () => {
      return await 
    }
  )

  return (
    <div className="App">
      <h1>Hello</h1>
    </div>
  );
}

export default App;
