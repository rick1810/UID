# UID

Generate unique identifiers.<br/>
<br/>
I just did not like the UUID standard,<br/>
so I made my own Unique Identifier.<br/>
<br/>
By adding the __version__ at the beggining instead of between the __time__,<br/>
The UID does not require to be the same length for all it's versions.<br/>
<br/>
<br/>

## Maven

> [!NOTE]
> Replace `version` with the latest version
```xml
<dependency>
  <groupId>com.dutch_computer_technology</groupId>
  <artifactId>uid</artifactId>
  <version>x.x.x</version>
</dependency>
```

## Jar download

You can download any stable or newest version in [/releases](https://github.com/rick1810/UID/releases)<br/>
<br/>
<br/>

## Versions

### v1

Generates a UID based on Time & Random,<br/>
Stringified as a 40 length hex separated by `-`<br/>

| Component | Value |
| --- | --- |
| _version_ | 1 |
| _time_ | Unix time [*High*, *Mid*, *Low*] |
| _key_ | SecureRandom 12bytes |

``01-0196D421177D-9D7E5978E35A9B26D6CDED51``

| _version_ | _time_ | _key_ |
| :---: | :---: | :---: |
| 01 | 0196D421177D | 9D7E5978E35A9B26D6CDED51 |
| 1 | 1747315660669 | 9D7E5978E35A9B26D6CDED51 |
