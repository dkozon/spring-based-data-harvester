# spring-based-data-harvester

Project is in design and development of modules phase - be patient - it will change frequently in near future.

**DataHarvester** is java utility for getting data from various web pages or Restful services.

## Configuration
For harvesting and saving data from web page you need to specify:

- source - URL of web page ready for harvesting data
- selector - list of CSS selectors for specified elements in web page)

**Example of harvesterConfig.json**:

```json
{
     "source": "http://www.79element.pl",
     "selectors": [
       "#spot_block_left > div:nth-child(2) > p:nth-child(1)"
     ]
}
```

Currently harvested data are displayed from console and apart of that stored in *.json and *.xml files.

Implementation for Console Log display is presented within _DataPresentationOnConsoleService_

Implementation for JSON files is presented within _DataPresentationOnJSONService_

Implementation for XML files is presented within _DataPresentationOnXMLService_

_DataPresentationServiceExploitable_ is example of vulnerable feature in one of opensource library (in future will be moved
another project).

