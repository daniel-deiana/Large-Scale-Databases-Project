import requests
import pandas as pd
from bs4 import BeautifulSoup as bsp

# scrapes web page to find associated anime to a certain character


def find_associated_anime(url):
    page = requests.get(url)
    soup = bsp(page.content, "html.parser")
    results = soup.find_all('a', attrs={"data-class": "tooltip-full"})
    return results[0].text


def main():

    DIR_DATASET = "../dataset/"
    dataframe = pd.read_csv(DIR_DATASET + 'characters_metadata.csv')
    dataframe = dataframe[dataframe["Love Count"] != "Unknown"]
    dataframe["Love Count"] = pd.to_numeric(dataframe["Love Count"])
    dataframe["anime"] = "Unknown"
    dataframe = dataframe[dataframe["Love Count"] > 300]

    column = []

    for row in dataframe.itertuples(index=True, name='Pandas'):
        column.append(find_associated_anime(row.url))

    print(len(column))
    dataframe["anime"] = column

    dataframe.to_csv(DIR_DATASET + "characters_scraped.csv")


if __name__ == "__main__":
    main()
