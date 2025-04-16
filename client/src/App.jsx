import { useState } from "react";
import Content from "./components/content";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";

const BASE_URL = "http://localhost:8080/api/content";

function App() {
  const [title, setTitle] = useState("");
  const queryClient = useQueryClient();

  const {
    data: content,
    error,
    isLoading,
  } = useQuery({
    queryKey: ["content"],
    queryFn: getContent,
  });

  const createContentMutation = useMutation({
    mutationFn: createContent,
    onSuccess: () => {
      setTitle("");
      queryClient.invalidateQueries({ queryKey: ["content"] });
    },
  });

  const deleteContentMutation = useMutation({
    mutationFn: deleteContent,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["content"] });
    },
  });

  function getContent() {
    return fetch(BASE_URL).then((res) => res.json());
  }

  function createContent() {
    const newContent = {
      id: content.length + 1,
      title,
      description: "My second blog post",
      status: "IDEA",
      type: "ARTICLE",
      dateCreated: "2025-02-05T10:25:02.838496",
      dateUpdated: null,
      url: "",
    };
    return fetch(BASE_URL, {
      method: "POST",
      body: JSON.stringify(newContent),
      headers: {
        "Content-Type": "application/json",
      },
    });
  }

  function submitContent(e) {
    e.preventDefault();
    createContentMutation.mutate();
  }

  function deleteContent(id) {
    return fetch(`${BASE_URL}/${id}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    });
  }

  return (
    <main
      style={{
        height: "100%",
        width: "100%",
        display: "grid",
        placeContent: "center",
      }}
    >
      <h1 style={{ textAlign: "center" }}>Content</h1>
      {isLoading && <h1>loading...</h1>}
      {error && <h2 style={{ color: "red" }}>{error}</h2>}
      {!isLoading && !error && (
        <div>
          {content?.map(({ id, title, status }) => (
            <div
              key={id}
              style={{ display: "flex", alignItems: "center", gap: "0.5rem" }}
            >
              <p>{id}</p>
              <h3>{title}</h3>
              <p>{status}</p>
              <button onClick={() => deleteContentMutation.mutate(id)}>
                Delete
              </button>
            </div>
          ))}
        </div>
      )}
      <form onSubmit={submitContent}>
        <label htmlFor="add-content-title">Title</label>
        <input
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="Example blogpost..."
        />
        <button type="submit">Create content</button>
      </form>
    </main>
  );
}

export default App;
