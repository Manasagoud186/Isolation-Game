import dash
from dash import dcc, html, Input, Output
import plotly.express as px
import pandas as pd

# Sample dataset
df = px.data.iris()  # Iris dataset with species and measurements

# Initialize the Dash app
app = dash.Dash(__name__)

app.layout = html.Div([
    html.H1("Advanced Interactivity with Dash & Plotly"),

    # Dropdown to select species
    html.Label("Select Species:"),
    dcc.Dropdown(
        id='species-dropdown',
        options=[{'label': s, 'value': s} for s in df['species'].unique()],
        value='setosa',
        clearable=False
    ),

    # Slider to filter sepal length
    html.Label("Filter by Sepal Length:"),
    dcc.Slider(
        id='sepal-slider',
        min=df['sepal_length'].min(),
        max=df['sepal_length'].max(),
        step=0.1,
        value=df['sepal_length'].mean(),
        marks={round(val,1): str(round(val,1)) for val in df['sepal_length'].unique()[::10]}
    ),

    # Graph output
    dcc.Graph(id='scatter-plot')
])

# Callback for interactivity
@app.callback(
    Output('scatter-plot', 'figure'),
    Input('species-dropdown', 'value'),
    Input('sepal-slider', 'value')
)
def update_graph(selected_species, sepal_length_threshold):
    # Filter dataset based on inputs
    filtered_df = df[(df['species'] == selected_species) &
                     (df['sepal_length'] >= sepal_length_threshold)]

    # Create scatter plot
    fig = px.scatter(
        filtered_df,
        x='sepal_width',
        y='petal_length',
        color='species',
        size='petal_width',
        title=f"Scatter Plot for {selected_species} (Sepal Length ≥ {sepal_length_threshold})"
    )
    return fig

if __name__ == '__main__':
    app.run(debug=True, port=8054)
