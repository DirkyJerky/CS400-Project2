# This makefile's dynamic, yo!
# Helped to be made courtesy of stackoverflow.com/questions/29184999


# APP_SRCS := $(shell find ./src -path ./src/test -prune -false -o -type f -name '*.java')
# TEST_SRCS := $(shell find ./src/test -type f -name '*.java') 
# SRCS := $(shell find ./src -type f -name '*.java')

# APP_CLASSES := $(APP_SRCS:src/%.java=bin/%.class)
# TEST_CLASSES := $(TEST_SRCS:src/%.java=bin/%.class)
# CLASSES := $(SRCS:.java=.class)

# JC := javac
# JCFLAGS := -d src/ -cp junit5.jar:src/

# src/%.class: src/%.java
# 	$(JC) $(JCFLAGS) $<

# Other goals

# clean:
	# $(RM) junit5.jar
	# $(RM) $(CLASSES)
# A generic makefile for a Java project.

# Location of trees.
SOURCE_DIR  := src
OUTPUT_DIR  := bin

# Unix tools
AWK         := awk
FIND        := find
MKDIR       := mkdir -p
RM          := rm -rf
SHELL       := bash

# Java tools
JAVA        := java
JAVAC       := javac

JFLAGS      := -sourcepath $(SOURCE_DIR)        \
               -d $(OUTPUT_DIR)                 \
			   -cp *:.

JUNIT_JAR   := junit5.jar

# Set the Java classpath
class_path := OUTPUT_DIR                \
              JUNIT_JAR

# space - A blank space
space := $(empty) $(empty)

# $(call build-classpath, variable-list)
define build-classpath
$(strip                                         \
  $(patsubst :%,%,                              \
    $(subst : ,:,                               \
      $(strip                                   \
        $(foreach j,$1,$(call get-file,$j):)))))
endef

# $(call get-file, variable-name)
define get-file
  $(strip                                       \
    $($1)                                       \
    $(if $(call file-exists-eval,$1),,          \
      $(warning The file referenced by variable \
                '$1' ($($1)) cannot be found)))
endef

# $(call file-exists-eval, variable-name)
define file-exists-eval
  $(strip                                       \
    $(if $($1),,$(warning '$1' has no value))   \
    $(wildcard $($1)))
endef

# $(call brief-help, makefile)
define brief-help
  $(AWK) '$$1 ~ /^[^.][-A-Za-z0-9]*:/                   \
         { print substr($$1, 1, length($$1)-1) }' $1 |  \
  sort
endef

# $(call file-exists, wildcard-pattern)
file-exists = $(wildcard $1)

# $(call check-file, file-list)
define check-file
  $(foreach f, $1,                              \
    $(if $(call file-exists, $($f)),,           \
      $(warning $f ($($f)) is missing)))
endef

# #(call make-temp-dir, root-opt)
define make-temp-dir
  mktemp -t $(if $1,$1,make).XXXXXXXXXX
endef

# Set the CLASSPATH
export CLASSPATH := $(call build-classpath, $(class_path))

# make-directories - Ensure output directory exists.
make-directories := $(shell $(MKDIR) $(OUTPUT_DIR))

.PHONY: all
all: run
	@echo
	@echo
	@echo \(Run 'make test' to run test suite\)

# help - The default goal
.PHONY: help
help:
	@echo 'Goals:'
	@$(call brief-help, Makefile)

# all_javas - Temp file for holding source file list
all_javas := $(OUTPUT_DIR)/all.javas

# compile - Compile the source
.PHONY: compile
compile: $(all_javas)
	$(JAVAC) $(JFLAGS) @$<
	@$(RM) $(all_javas)

# all_javas - Gather source file list
# .INTERMEDIATE: $(all_javas)
$(all_javas):
	$(FIND) $(SOURCE_DIR) -name '*.java' > $@

.PHONY: clean
clean:
	$(RM) $(OUTPUT_DIR)


# Fetch junit (also defined $JUNIT_JAR on line 44 above)
junit5.jar:
	wget http://pages.cs.wisc.edu/~cs400/junit5.jar

.PHONY: run
run: compile
	java -cp *:$(OUTPUT_DIR) frontend.Main

.PHONY: test
test: $(JUNIT_JAR) compile
	java -jar $(JUNIT_JAR) --class-path ./bin --scan-classpath --details tree
