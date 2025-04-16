const Content = ({ title, status, id }) => {
  return (
    <div style={{ display: "flex", alignItems: "center", gap: "0.5rem" }}>
      <p>{id}</p>
      <h3>{title}</h3>
      <p>{status}</p>
    </div>
  );
};

export default Content;
