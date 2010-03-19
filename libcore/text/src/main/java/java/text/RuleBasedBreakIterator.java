/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// BEGIN android-note
// The icu implementation used was changed from icu4j to icu4jni.
// END android-note

package java.text;

import com.ibm.icu4jni.text.NativeBreakIterator;

/*
 * Default implementation of BreakIterator. Wraps com.ibm.icu4jni.text.NativeBreakIterator.
 * We need this because BreakIterator.isBoundary and BreakIterator.preceding are non-abstract,
 * and we don't have Java implementations of those methods (other than the current ones, which
 * forward to the wrapped NativeBreakIterator).
 */
class RuleBasedBreakIterator extends BreakIterator {

    /*
     * Wrapping constructor.
     */
    RuleBasedBreakIterator(NativeBreakIterator iterator) {
        super(iterator);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.text.BreakIterator#current()
     */
    @Override
    public int current() {
        return wrapped.current();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.text.BreakIterator#first()
     */
    @Override
    public int first() {
        return wrapped.first();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.text.BreakIterator#following(int)
     */
    @Override
    public int following(int offset) {
        validateOffset(offset);
        return wrapped.following(offset);
    }

    /*
     * check the offset, throw exception if it is invalid
     */
    private void validateOffset(int offset) {
        CharacterIterator it = wrapped.getText();
        if (offset < it.getBeginIndex() || offset >= it.getEndIndex()) {
            throw new IllegalArgumentException();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.text.BreakIterator#getText()
     */
    @Override
    public CharacterIterator getText() {
        return wrapped.getText();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.text.BreakIterator#last()
     */
    @Override
    public int last() {
        return wrapped.last();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.text.BreakIterator#next()
     */
    @Override
    public int next() {
        return wrapped.next();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.text.BreakIterator#next(int)
     */
    @Override
    public int next(int n) {
        return wrapped.next(n);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.text.BreakIterator#previous()
     */
    @Override
    public int previous() {
        return wrapped.previous();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.text.BreakIterator#setText(java.text.CharacterIterator)
     */
    @Override
    public void setText(CharacterIterator newText) {
        // call a method to check if null pointer
        newText.current();
        wrapped.setText(newText);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.text.BreakIterator#isBoundary(int)
     */
    @Override
    public boolean isBoundary(int offset) {
        validateOffset(offset);
        return wrapped.isBoundary(offset);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.text.BreakIterator#preceding(int)
     */
    @Override
    public int preceding(int offset) {
        validateOffset(offset);
        return wrapped.preceding(offset);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RuleBasedBreakIterator)) {
            return false;
        }
        return wrapped.equals(((RuleBasedBreakIterator) o).wrapped);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return wrapped.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return wrapped.hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() {
        RuleBasedBreakIterator cloned = (RuleBasedBreakIterator) super.clone();
        cloned.wrapped = (NativeBreakIterator) wrapped.clone();
        return cloned;
    }
}
