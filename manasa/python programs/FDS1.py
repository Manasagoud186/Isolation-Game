import dash
from dash import dcc, html
import plotly.express as px
import pandas as pd

# Sample Data
df = pd.DataFrame({
    "Month": ["Jan", "Feb", "Mar", "Apr", "May", "Jun"],
    "Sales": [100, 200, 150, 300, 250, 400],
    "Profit": [20, 50, 40, 80, 60, 90]
})

# Create Figures
fig_line = px.line(
    df, x="Month", y="Sales",
    title="Monthly Sales Trend",
    markers=True
)

fig_bar = px.bar(
    df, x="Month", y="Profit",
    title="Monthly Profit"
)

# Initialize Dash App
app = dash.Dash(__name__)

app.layout = html.Div([
    html.H1(
        "Sales Dashboard",
        style={'textAlign': 'center'}
    ),

    dcc.Graph(
        id="sales-line-chart",
        figure=fig_line
    ),

    dcc.Graph(
        id="profit-bar-chart",
        figure=fig_bar
    )
])

# Run Server
if __name__ == "__main__":
    app.run(debug=True)   # Updated for Dash 3.x
