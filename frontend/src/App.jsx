function App() {
  return (
    <div className="app-shell">
      <header className="topbar">
        <div>
          <h1>Hotel Booking System</h1>
          <p>Your React frontend for hotel reservations.</p>
        </div>
      </header>

      <main>
        <section className="hero-card">
          <h2>Welcome to the booking portal</h2>
          <p>
            Build the UI here and connect to the backend API in
            <code>Hotel_booking_System</code> when ready.
          </p>
        </section>

        <section className="features-grid">
          <article>
            <h3>View rooms</h3>
            <p>Fetch room availability and show available hotels.</p>
          </article>
          <article>
            <h3>Book a stay</h3>
            <p>Submit booking details and confirm reservations.</p>
          </article>
          <article>
            <h3>Manage users</h3>
            <p>Handle login, registration, and profile actions.</p>
          </article>
        </section>
      </main>
    </div>
  );
}

export default App;
