# Python's analysers: call expressions

Functionality related to call expressions analysis in Python projects.
This analyzer allows extracting functions/classes/decorators calls.

## The pipeline of the running this module

#### 1. Download list of repositories

You can use [seart-ghs.si.usi.ch]( https://seart-ghs.si.usi.ch/) (main language: Python) for this purpose. The file has
to be in csv format with ```name``` column, containing repositories' names in the following
format: ```username/project_name```

#### 2. Clean duplicated repositories

Run the script for cleaning all duplicated repositories with different names:
``` shell script
python3 -m scripts/data_collection/clean_duplicates /path/to/csv_file/results.csv /path/to/cleaned/data/dir
```
You can use the ```--save-metadata``` flag to download metadata about all projects.
Metadata includes the information about repository's full name, owner, etc.
This script makes requests to GitHub API,
so you should add your GitHub Token to environment variables
(variable name is ```GITHUB_TOKEN```).
To get more information, please see the [README](../../../../../../../../../../scripts/data_collection/README.md)
file from the [`data_collection`](../../../../../../../../../../scripts/data_collection) module.

#### 3. Load dataset

Run the following command to download the dataset:

``` 
python3 -m scripts/data_collection/load_dataset /path/to/cleaned/data/dir/results.csv /path/to/dataset/dir --allowed-extensions py
```

To get more information, please see the [README](../../../../../../../../../../scripts/data_collection/README.md)
file from the [`data_collection`](../../../../../../../../../../scripts/data_collection) module.

#### 4. Set up a virtual environment (Optional)

The virtual environment is necessary to resolve external dependencies in project. 
If you don't set up it, you can not extract libraries' names for functions, classes and decorators calls.

Run the following command to set up a virtual environment:

``` 
python3 -m scripts/plugin_runner/create_venv /path/to/dataset/dir /path/to/venv
```

To get more information, please see the [README](../../../../../../../../../../scripts/plugin_runner/README.md)
file from the [`plugin_runner`](../../../../../../../../../../scripts/plugin_runner) module.

#### 5. Extract call expressions full-qualified names from dataset

Run the analyzer directly:
``` 
./gradlew :lupa-runner:python-cli -Prunner=python-call-expressions-analysis -Pinput=/path/to/dataset/dir -Poutput=path/to/results/dir -Pvenv=/path/to/venv
```
Or you can handle the dataset via batches using batch processing:
``` 
python3 -m scripts/plugin_runner/batch_processing /path/to/dataset/dir /path/to/extracted_methods/dir python-call-expressions --task-name python-cli --kwargs venv=path/to/venv
```
In this case you can use the ```--batch-size n``` option (default value is 300).
Also, there is ```--start-from n``` option to start from batch with given number.
To get more information, please see the [README](../../../../../../../../../../scripts/plugin_runner/README.md)
file from the [`plugin_runner`](../../../../../../../../../../scripts/plugin_runner) module.

**Note**: if you don't want to run the analysis with a virtual environment, just pass an empty string as path. For example: `-Pvenv=""`.

#### 6. Run statistics visualisation

To read more about call expressions statistics visualization, please see the [README](../../../../../../../../../../scripts/analysis/call_expressions/python/README.md) file from the [`call_expressions`](../../../../../../../../../../scripts/analysis/call_expressions/python) module.